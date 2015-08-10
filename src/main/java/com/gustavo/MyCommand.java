package com.gustavo;


public class MyCommand {

    private String description;
    private String hotResponse;
    private String coldResponse;

    public MyCommand(String description, String hotResponse, String coldResponse){
        setDescription(description);
        setHotResponse(hotResponse);
        setColdResponse(coldResponse);
    }

    public String getColdResponse() {
        return coldResponse;
    }

    public void setColdResponse(String coldResponse) {
        this.coldResponse = coldResponse;
    }

    public String getHotResponse() {
        return hotResponse;
    }

    public void setHotResponse(String hotResponse) {
        this.hotResponse = hotResponse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponse(String temperature) {
        if ("HOT".equals(temperature)) {
            return getHotResponse();
        }else {
            return getColdResponse();
        }
    }


}
