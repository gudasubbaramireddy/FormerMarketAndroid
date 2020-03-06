package com.thrymr.farmersmarket.Model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by thrymr.
 *
 *   Model class of Events with images to save in local database and send them to OTM server in captured sequence.
 *
 */

@Entity(tableName = "server_image_event")
public class ServerImageEvent {

    @PrimaryKey(autoGenerate = true)
    private int serverImageEventId;
    private String eventName;
    private Boolean status;
    private String timeOfEvent, body, url;
    private String ownerId;
    private String image;
    private String submitStatus;

    public ServerImageEvent() {

    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTimeOfEvent() {
        return timeOfEvent;
    }

    public void setTimeOfEvent(String timeOfEvent) {
        this.timeOfEvent = timeOfEvent;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(String submitStatus) {
        this.submitStatus = submitStatus;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getServerImageEventId() {
        return serverImageEventId;
    }

    public void setServerImageEventId(int serverImageEventId) {
        this.serverImageEventId = serverImageEventId;
    }
}
