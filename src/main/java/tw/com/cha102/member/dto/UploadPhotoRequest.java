package tw.com.cha102.member.dto;

import java.util.Base64;

public class UploadPhotoRequest {
    private byte[] memberPhoto;

    public String getMemberPhoto() {
        if (this.memberPhoto != null) {
            return Base64.getEncoder().encodeToString(this.memberPhoto);
        } else {
            return null;
        }
    }

}
