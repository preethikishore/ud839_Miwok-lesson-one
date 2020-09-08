package com.example.android.miwok;

public class Word {

    private String sourceInput;
    private String targetInput;
    private int audioId;
    private int imageId = NO_IMAGE_VALUE;
    private static final int NO_IMAGE_VALUE = -1;




    public Word(String sInput, String tInput)
    {
       this.sourceInput = sInput;
       this.targetInput = tInput;
    }
    public Word(String sInput, String tInput,int inputImage,int audioId)
    {
        this.sourceInput = sInput;
        this.targetInput = tInput;
        this.imageId = inputImage;
        this.audioId = audioId;
    }

    public String getSourceInput()
    {
        return sourceInput;
    }
    public String getTargeInput()
    {
        return targetInput;
    }
    public Integer getImageInput()
    {
        return imageId;
    }
    public Integer getaudioId()
    {
        return audioId;
    }
    public  boolean HasImage()
    {
        return imageId != NO_IMAGE_VALUE;
    }
    @Override
    public String toString() {
        return "Word{" +
                "sourceInput='" + sourceInput + '\'' +
                ", targetInput='" + targetInput + '\'' +
                ", audioId=" + audioId +
                ", imageId=" + imageId +
                '}';
    }
}
