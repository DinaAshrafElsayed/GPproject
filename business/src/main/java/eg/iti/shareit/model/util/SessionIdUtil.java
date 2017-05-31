/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.util;

import java.security.SecureRandom;
import java.math.BigInteger;

/**
 *
 * @author Dina Ashraf
 */
public final class SessionIdUtil {

    private SecureRandom random = new SecureRandom();

    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }
}