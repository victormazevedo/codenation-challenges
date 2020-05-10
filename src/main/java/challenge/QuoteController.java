package challenge;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class QuoteController {

    private final QuoteService service;

    public QuoteController(QuoteService service) {
        this.service = service;
    }

    @GetMapping("/quote")
    public ResponseEntity<Quote> getQuote() {
        return ResponseEntity.ok(service.getQuote());
    }

    @GetMapping("/quote/{actor}")
    public ResponseEntity<Quote> getQuoteByActor(@PathVariable("actor") String actor) {
        return ResponseEntity.ok(service.getQuoteByActor(actor));
    }

}
