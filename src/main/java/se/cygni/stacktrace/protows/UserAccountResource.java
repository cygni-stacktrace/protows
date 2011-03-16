package se.cygni.stacktrace.protows;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import se.cygni.stacktrace.protows.UserAccountProtos.UserAccount;
import se.cygni.stacktrace.protows.UserAccountProtos.UserAccount.AccountState;

@Path("/account")
public class UserAccountResource {
	@GET
	@Produces("application/x-protobuf")
	public UserAccount getUserAccount() {
		return UserAccountProtos.UserAccount.newBuilder().setId(100)
				.setUserName("jon.edvardsson")
				.setState(AccountState.PENDING_TANDC).addService("BOOKS")
				.addService("MOVIES").addService("MUSIC").build();
	}

	@PUT
	@Consumes("application/x-protobuf")
	@Produces("application/x-protobuf")
	public UserAccount acceptTermsAndConditions(UserAccount account) {
		return UserAccount.newBuilder(account)
			.setState(AccountState.ACTIVE).build();
	}
}
