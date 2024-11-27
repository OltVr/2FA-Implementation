package com.example.a2fa_class;

import java.security.SecureRandom;

public class OTPGenerator {
        public static String generate(int length) {
            // Define the characters to be used in the OTP
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

            // Use SecureRandom for better randomness
            SecureRandom random = new SecureRandom();

            // StringBuilder for efficient string manipulation
            StringBuilder otp = new StringBuilder(length);

            // Generate OTP
            for (int i = 0; i < length; i++) {
                int index = random.nextInt(characters.length());
                otp.append(characters.charAt(index));
            }

            return otp.toString();
        }
    }
