package com.example.digitalelection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {
    static long  id;
    public static boolean isValid(int voterid, String userPin) throws Exception {
        Connection con = DataBaseConnection.getConnection();
        String query = "SELECT status FROM voters WHERE voterid = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, voterid);
        id=voterid;
        ResultSet rs = ps.executeQuery();
        System.out.println(rs);
        if (rs.next()) {
            int status = rs.getInt("status");
            System.out.println("Status is "+status);
            if (status == 0) {
                System.out.println("Not voted");
                String query1 = "update voters set status=1 where voteid= ?";
                PreparedStatement ps1 = con.prepareStatement(query1);
                ps1.setString(1, String.valueOf(LoginDAO.id));
                int i = ps.executeUpdate();


                System.out.println("Voted"+i);
                String loginQuery = "SELECT * FROM voters WHERE voterid = ? AND password = ?";
                PreparedStatement loginPs = con.prepareStatement(loginQuery);
                loginPs.setInt(1, voterid);
                loginPs.setString(2, userPin);
                ResultSet loginRs = loginPs.executeQuery();
                System.out.println("The data");
                System.out.println(loginRs);
                if (loginRs.next()) {

                    return true; // Voter credentials are valid
                }
                return false; // Status indicates invalid or inactive voter

            }
            else{
                return true;
            }
        } else {
            return false; // No voter found with the given voterid
        }

    }
}

