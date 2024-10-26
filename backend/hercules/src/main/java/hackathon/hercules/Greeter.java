package hackathon.hercules;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Greeter {

    public class Greeting {
        private final long id;
        private final String text;

        public Greeting(long id, String text) {
            this.id = id;
            this.text = text;
        }
        public long getId() { return id; }

        public String getContent() { return text; }
    }

    @GetMapping("/api/test")
    @ResponseBody
    public Greeting sayHello() {
        return new Greeting(0, "heelo");
    }
}
