@RestController
public class hello{
	@RequestMapping(value="/")
	public String greet(){
		"Hello, Everyone";
	}
}