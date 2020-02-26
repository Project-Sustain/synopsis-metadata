package sustain.synopsis.metadata.rest.query;

import org.springframework.web.bind.annotation.*;

@RestController
public class QueryController {

    @RequestMapping(value="/api/query", method= RequestMethod.POST)
    @ResponseBody
    public String query(@RequestBody String s) {

        return "";
    }


}
