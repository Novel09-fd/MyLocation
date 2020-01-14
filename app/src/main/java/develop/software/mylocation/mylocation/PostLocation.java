
package develop.software.mylocation.mylocation;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostLocation implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<PostLocation> CREATOR = new Creator<PostLocation>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PostLocation createFromParcel(Parcel in) {
            return new PostLocation(in);
        }

        public PostLocation[] newArray(int size) {
            return (new PostLocation[size]);
        }

    }
    ;
    private final static long serialVersionUID = 2776137377799429082L;

    protected PostLocation(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public PostLocation() {
    }

    /**
     * 
     * @param message
     * @param status
     */
    public PostLocation(Boolean status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
    }

    public int describeContents() {
        return  0;
    }

}
