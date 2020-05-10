package challenge;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuoteServiceImpl implements QuoteService {

	private final QuoteRepository repository;

	public QuoteServiceImpl(QuoteRepository repository) {
		this.repository = repository;
	}

	@Override
	public Quote getQuote() {
		return repository.findRandomQuote();
	}

	@Override
	public Quote getQuoteByActor(String actor) {
		List<Quote> quotes = repository.findAllByActor(actor);
		return repository.findAllByActor(actor).get(new Random().nextInt(quotes.size()));
	}

}
