package events.web;

import events.models.CulturalEvent;
import events.parsers.HtmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Demo controller.
 */
@RestController
@RequestMapping("/api/demo")
public class DemoController {
    // controller just for debugging purposes for now

    @Autowired
    private HtmlParser parser;

    @RequestMapping("")
    public List<CulturalEvent> demo() {
        return parser.parse();
    }
}
