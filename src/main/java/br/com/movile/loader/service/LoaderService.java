package br.com.movile.loader.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import br.com.movile.customer.model.Customer;
import br.com.movile.item.model.Item;
import br.com.movile.motoboy.model.Motoboy;
import br.com.movile.restaurant.model.Restaurant;

public class LoaderService {

	private Path path = null;
	private List<String> allLines;
	private List<Customer> customers;
	private List<Motoboy> motoboy;
	private List<Restaurant> restaurants;
	private List<Item> items;

	private static final char DEFAULT_SEPARATOR = ',';
	private static final char DEFAULT_QUOTE = '"';

	private String getFile(String fileName){
		ClassLoader classLoader = LoaderService.class.getClassLoader();
		String path = Objects.requireNonNull(classLoader.getResource("csv/" + fileName)).getPath();

		return path.replace("/C:", "");
	}

	public void customer() {

		path = Paths.get(getFile("clientes.csv"));

		try {
			allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
			customers = new ArrayList<>();
			
			allLines.stream().skip(1).forEach(x -> {

				List<String> parseLine = parseLine(x, DEFAULT_SEPARATOR, DEFAULT_QUOTE);

				String id = parseLine.get(0).replaceAll(",", "");
				

				GeoJsonPoint location = new GeoJsonPoint(Double.parseDouble(parseLine.get(1)), Double.parseDouble(parseLine.get(2)));
				
				customers.add(new Customer(id, location));
			});

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void motoboy() {
		path = Paths.get(getFile("motoboys.csv"));

		try {
			allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
			motoboy = new ArrayList<>();
			allLines.stream().skip(1).forEach(x -> {

				List<String> parseLine = parseLine(x, DEFAULT_SEPARATOR, DEFAULT_QUOTE);

				String id = parseLine.get(0).replaceAll(",", "");				
				GeoJsonPoint location = new GeoJsonPoint(new Point(Double.parseDouble(parseLine.get(1)), Double.parseDouble(parseLine.get(2))));

				motoboy.add(new Motoboy(id, location, false));
			});

		} catch (Exception e1) {
			System.out.println("Deu ruim no Motoboy : ");
			e1.printStackTrace();
		}

	}

	public void restaurantsByCity() {

		path = Paths
				.get(getFile("estabelecimento-por-municipio.csv"));

		restaurants = new ArrayList<>();

		try {

			allLines = Files.readAllLines(path, StandardCharsets.UTF_8);

			allLines.stream().skip(1).forEach(x -> {
				List<String> parseLine = parseLine(x, DEFAULT_SEPARATOR, DEFAULT_QUOTE);

				if (parseLine.size() == 1) {
					String li = "\"".concat(parseLine.get(0)).split(",")[0].concat("\"");
					String[] split = parseLine.get(0).split(",");
					split[0] = li;
					StringBuilder builder = new StringBuilder();
					for (String string : split) {
						builder.append(string.concat(","));
					}
					parseLine.clear();
					parseLine = parseLine(builder.toString(), DEFAULT_SEPARATOR, DEFAULT_QUOTE);
				}

				String id = parseLine.get(0);
				String name = parseLine.get(1);
				String city = parseLine.get(2);
				GeoJsonPoint location = new GeoJsonPoint(new Point(Double.parseDouble(parseLine.get(3)), Double.parseDouble(parseLine.get(4))));
				String description = parseLine.get(5).replaceAll(";", "");

				restaurants.add(new Restaurant(id, name, city, location, description));
			});

			itemsByRestaurants();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}

	public void itemsByRestaurants() {
		path = Paths
				.get(getFile("produtos-por-estabelecimento.csv"));

		try {
			items = new ArrayList<>();
			allLines = Files.readAllLines(path, StandardCharsets.UTF_8);

			allLines.stream().skip(1).forEach(x -> {

				List<String> parseLine = parseLine(x, DEFAULT_SEPARATOR, DEFAULT_QUOTE);

				String id = parseLine.get(1);
				String description = parseLine.get(0);
				String restaurantId = parseLine.get(3);
				String restaurant = parseLine.get(2);
				String classification = parseLine.get(4);
				BigDecimal unitPrice = new BigDecimal(parseLine.get(5));
				String city = parseLine.get(6);

				items.add(
						new Item(id, description, restaurantId, restaurant, classification, unitPrice, city));
			});

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Errrrooooooo");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("Errooooooo");
		}
	}

	public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

		List<String> result = new ArrayList<>();

		// if empty, return!
		if (cvsLine == null || cvsLine.isEmpty()) {
			return result;
		}

		if (customQuote == ' ') {
			customQuote = DEFAULT_QUOTE;
		}

		if (separators == ' ') {
			separators = DEFAULT_SEPARATOR;
		}

		StringBuffer curVal = new StringBuffer();
		boolean inQuotes = false;
		boolean startCollectChar = false;
		boolean doubleQuotesInColumn = false;

		char[] chars = cvsLine.toCharArray();

		for (char ch : chars) {

			if (inQuotes) {
				startCollectChar = true;
				if (ch == customQuote) {
					inQuotes = false;
					doubleQuotesInColumn = false;
				} else {

					// Fixed : allow "" in custom quote enclosed
					if (ch == '\"') {
						if (!doubleQuotesInColumn) {
							curVal.append(ch);
							doubleQuotesInColumn = true;
						}
					} else {
						curVal.append(ch);
					}

				}
			} else {
				if (ch == customQuote) {

					inQuotes = true;

					// Fixed : allow "" in empty quote enclosed
					if (chars[0] != '"' && customQuote == '\"') {
						curVal.append('"');
					}

					// double quotes in column will hit this!
					if (startCollectChar) {
						curVal.append('"');
					}

				} else if (ch == separators) {

					result.add(curVal.toString());

					curVal = new StringBuffer();
					startCollectChar = false;

				} else if (ch == '\r') {
					// ignore LF characters
					continue;
				} else if (ch == '\n') {
					// the end, break!
					break;
				} else {
					curVal.append(ch);
				}
			}

		}

		result.add(curVal.toString());

		return result;
	}

	public void load() {
		customer();
		motoboy();
		restaurantsByCity();
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public List<Motoboy> getMotoboy() {
		return motoboy;
	}

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public List<Item> getItems() {
		return items;
	}
}