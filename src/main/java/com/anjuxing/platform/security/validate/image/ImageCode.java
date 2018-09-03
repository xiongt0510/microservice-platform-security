package com.anjuxing.platform.security.validate.image;

import com.anjuxing.platform.security.validate.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author xiongt
 * @Description  图像验证码
 */
public class ImageCode extends ValidateCode {

    //图片
    private BufferedImage image;


    public ImageCode(BufferedImage image, String code, int expireIn){
        super(code, expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
        super(code, expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
