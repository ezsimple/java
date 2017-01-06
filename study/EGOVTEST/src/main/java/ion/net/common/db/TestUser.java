package ion.net.common.db;

import lombok.Data;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data public class TestUser {
	String name;
	String email;
	String address;
	int group;
}