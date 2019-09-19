package com.qtasnim.qhopes.utils;

public interface OtpReceivedInterface {
    void onOtpReceived(String otp);
    void onOtpTimeout();
}
