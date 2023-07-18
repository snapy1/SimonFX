package personal.proj.simonfx;

import javafx.scene.control.Button;

/**
 * @author Nicholas G
 *
 * This class is used to control what the buttons do and how they react inside the simon game.
 */
public class ButtonAttributes {

    private final Button[] buttonArr = new Button[4];

    /**
     * Creates X amount of Button objects of the desired X
     * amount of buttons which is determined by out
     * private buttonArr array.
     */
    public void createButtonsOfArrLength(){
        for (int i = 0; i < buttonArr.length; i++) {
            buttonArr[i] = new Button();
        }
    }

    /**
     * Particularly meant for setting color(s) with our buttons
     * this allows us to select the index of our desired button and apply
     * a CSS setStyle attribute.
     * @param index the desired button within our buttonArr array
     * @param cssString the desired string (must be CSS)
     */
    public void buttonSetStyle(int index, String cssString){
        buttonArr[index].setStyle(cssString);
    }

    /**
     * Simply allows us to select one of our buttons from out buttonArr list.
     * @param indexOfDesiredButtonInArr our desired button associated with the numerical index of the array
     * @return returns the button object.
     */
    public Button getButton(int indexOfDesiredButtonInArr){
        return buttonArr[indexOfDesiredButtonInArr];
    }

    /**
     * Keeps a list of our CSS associated default slightly muted colors.
     * @param index selecting an index of the colors string
     * @return returns it as a string to be inserted into a setStyle method.
     */
    public String defaultColors(int index){
        String[] colors = {"-fx-background-color: #DEE263", "-fx-background-color: #639AE2", "-fx-background-color: #E26363", "-fx-background-color: #85FC89"};
        return colors[index];
    }

    /**
     * Keeps a list of our CSS associated default slightly brighter colors.
     * @param index selecting an index of the colors string
     * @return returns it as a string to be inserted into a setStyle method.
     */
    public String brightColors(int index){
        String[] colors = {"-fx-background-color: #FFF700", "-fx-background-color: #004DFF", "-fx-background-color: #FF0044", "-fx-background-color: #59E943"};
        return colors[index];
    }

    /**
     * Upon clicking the button within the simon game it will brighten the color.
     */
    public void mouseOn(Button desiredButton, int indexOfColor, int buttonIndex, SimonGame gameName){
        desiredButton.setOnMousePressed(mouseEvent -> {
            desiredButton.setStyle(brightColors(indexOfColor));
            gameName.addToUserKey(buttonIndex);
        });
    }

    /**
     * Upon releasing the button within the simon game it will un-brighten the color.
     */
    public void mouseOff(Button desiredButton, int indexOfColor){
        desiredButton.setOnMouseReleased(mouseEvent -> {
            desiredButton.setStyle(defaultColors(indexOfColor));
        });
    }

    /**
     * Sets the array size of the game (typically 4 four for four buttons).
     */
    public void setSize(int indexOfButton, SimonGame gameName){
        buttonArr[indexOfButton].setPrefSize(gameName.getSceneX() / 2, gameName.getSceneY() / 2);
    }
}
