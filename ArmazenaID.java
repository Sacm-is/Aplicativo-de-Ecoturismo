package dao;

public class ArmazenaID {
	private static int userID;

	public static int getUserID() {
		return userID;
	}

	public static void setUserID(int userID) {
		ArmazenaID.userID = userID;
	}
}
