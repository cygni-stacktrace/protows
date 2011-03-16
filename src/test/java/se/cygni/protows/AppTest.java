package se.cygni.protows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import junit.framework.TestCase;
import se.cygni.stacktrace.protows.ProtobufMessageBodyWriter;
import se.cygni.stacktrace.protows.UserAccountProtos;
import se.cygni.stacktrace.protows.UserAccountProtos.UserAccount;
import se.cygni.stacktrace.protows.UserAccountProtos.UserAccount.AccountState;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	private static final int ITERATIONS = 1000000;
	private UserAccount userAccount = UserAccountProtos.UserAccount.newBuilder().setId(100)
	.setUserName("jon.edvardsson").setState(AccountState.SUSPENDED)
	.addService("BOOKS").addService("MOVIES").addService("MUSIC").build();
	
	public int result = 0;
	
	
	public void testBufferSize() {
		final ProtobufMessageBodyWriter writer = new ProtobufMessageBodyWriter();
		benchmark("bufferSize", ITERATIONS, new Runnable() {
			@Override
			public void run() {
				result += writer.getSize(userAccount, null, null, null, null);
			}
		});
		
		
	}
	
	
	public void testSerializedSize() {
		benchmark("searializedSize", ITERATIONS, new Runnable() {
			@Override
			public void run() {
				result += userAccount.getSerializedSize();
			}
		});
	}
	
	public void testWriteTo() {
		benchmark("writeTo", ITERATIONS, new Runnable() {
	        ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
			@Override
			public void run() {
                try {
					userAccount.writeTo(baos);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
	            result += baos.size();
		        baos.reset();
			}
		});
	}
	
	public void testToString() {
		benchmark("toString", ITERATIONS, new Runnable() {
			@Override
			public void run() {
				result += userAccount.toString().length();
			}
		});
	}


	
	public void benchmark(String name, int n, Runnable r) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			r.run();
		}
		long dur = System.currentTimeMillis()-start;
		System.out.printf("%s: (%d) %.2f ms%n", name, result, dur/((double) n/10000));
	}
}
