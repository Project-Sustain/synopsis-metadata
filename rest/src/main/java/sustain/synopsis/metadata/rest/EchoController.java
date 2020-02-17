package sustain.synopsis.metadata.rest;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class EchoController {

    @RequestMapping(value="/api/echo/{msg}", method=RequestMethod.POST)
    @ResponseBody
    public String echo(@PathVariable("msg") String msg) {
        return msg;
    }

    @RequestMapping(value="/api/echo2", method=RequestMethod.POST)
    @ResponseBody
    public Echo echo(@RequestBody @Valid Echo echo) {
        return echo;
    }

}
