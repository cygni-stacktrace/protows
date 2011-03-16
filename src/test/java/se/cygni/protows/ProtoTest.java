package se.cygni.protows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import junit.framework.TestCase;
import se.cygni.stacktrace.protows.UserAccountProtos;
import se.cygni.stacktrace.protows.UserAccountProtos.UserAccount;
import se.cygni.stacktrace.protows.UserAccountProtos.UserAccount.AccountState;

public class ProtoTest extends TestCase {
	public void test1() throws Exception {
		UserAccount a1 = 
			UserAccountProtos.UserAccount.newBuilder()
				.setId(100)
				.setUserName("jon.edvardsson")
				.setState(AccountState.SUSPENDED)
				.addService("BOOKS")
				.addService("MOVIES")
				.addService("MUSIC").build();
		System.out.println(a1);
		System.out.println(a1.toByteString().toStringUtf8());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		a1.writeTo(out);
		
		ByteArrayInputStream in = new ByteArrayInputStream(a1.toByteArray());
		UserAccount a2 = UserAccountProtos.UserAccount.parseFrom(in);
		System.out.println(a2);
	}

}
