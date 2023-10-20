import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WeatherAppGui extends JFrame {

    private JSONObject weatherData;

        public WeatherAppGui() {
            super ("WeatherApp");

            setDefaultCloseOperation(EXIT_ON_CLOSE);

            setSize(450,650);

            setLocationRelativeTo(null);

            setLayout(null);

            setResizable(false);

            addGuiComponents();
        }
        private void addGuiComponents() {
            JTextField searchTextField = new JTextField();

            searchTextField.setBounds(15, 15, 351, 45);

            searchTextField.setFont(new Font("Dialog", Font.PLAIN,24));

            add(searchTextField);



            JLabel weatherConditionImage = new JLabel(loadImage("/Users/kristiyanmilenkov/Downloads/WeahterApp/src/assets/cloudy.png"));
            weatherConditionImage.setBounds(0, 125, 450, 217);
            add(weatherConditionImage);

            JLabel temperatureText = new JLabel("10 C");
            temperatureText.setBounds(0, 350, 450, 54);
            temperatureText.setFont(new Font("Dialog", Font.BOLD,48));

            temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
            add(temperatureText);

            JLabel weatherConditionText = new JLabel("Cloudy");
            weatherConditionText.setBounds(0, 405, 450, 36);
            weatherConditionText.setFont(new Font("Dialog", Font.PLAIN,32));
            weatherConditionText.setHorizontalAlignment(SwingConstants.CENTER);
            add(weatherConditionText);

            JLabel humidityImage = new JLabel(loadImage("/Users/kristiyanmilenkov/Downloads/WeahterApp/src/assets/humidity.png"));
            humidityImage.setBounds(15, 500, 74, 66);
            add(humidityImage);

            JLabel humidityText = new JLabel("<html><b>Humidity</b> 100%</html>");
            humidityText.setBounds(90, 500, 85, 55);
            humidityText.setFont(new Font("Dialog", Font.PLAIN,15));
            add(humidityText);

            JLabel windSpeedImage = new JLabel(loadImage("/Users/kristiyanmilenkov/Downloads/WeahterApp/src/assets/windspeed.png"));
            windSpeedImage.setBounds(220, 500, 74, 66);
            add(windSpeedImage);

            JLabel windSpeedText = new JLabel("<html><b>Windspeed</b> 15 km/h</html>");
            windSpeedText.setBounds(310, 500, 85, 55);
            windSpeedText.setFont(new Font("Dialog", Font.PLAIN,15));
            add(windSpeedText);

            JButton searchButton = new JButton(loadImage("/Users/kristiyanmilenkov/Downloads/WeahterApp/src/assets/search.png"));
            searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            searchButton.setBounds(375, 13, 47, 45);
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String userInput = searchTextField.getText();

                    if (userInput.replaceAll("\\s", "").length() <= 0) {
                        return;
                    }
                    weatherData = WeatherApp.getWeatherData(userInput);
                    String weatherCondition = (String) weatherData.get("weatherCondition");

                    switch (weatherCondition) {
                        case "Clear":
                            weatherConditionImage.setIcon(loadImage("/Users/kristiyanmilenkov/Downloads/WeahterApp/src/assets/clear.png"));
                            break;
                        case "Cloudy":
                            weatherConditionImage.setIcon(loadImage("/Users/kristiyanmilenkov/Downloads/WeahterApp/src/assets/cloudy.png"));
                            break;
                        case "Rain":
                            weatherConditionImage.setIcon(loadImage("/Users/kristiyanmilenkov/Downloads/WeahterApp/src/assets/rain.png"));
                            break;
                        case "Snow":
                            weatherConditionImage.setIcon(loadImage("/Users/kristiyanmilenkov/Downloads/WeahterApp/src/assets/snow.png"));
                            break;

                    }
                    double temperature = (Double) weatherData.get("temperature");
                    temperatureText.setText(temperature + " C");

                    weatherConditionText.setText(weatherCondition);

                    long humidity = (long) weatherData.get("humidity");
                    humidityText.setText("<html><b>Humidity</b> " + humidity + "%</html>");

                    double windSpeed = (double) weatherData.get("windSpeed");
                    windSpeedText.setText("<html><b>Windspeed</b> " + windSpeed + "km/h</html>");
                }

            });
            add(searchButton);
        }
        private ImageIcon loadImage(String resourcePath) {
            try {
                BufferedImage image  = ImageIO.read(new File(resourcePath));
                return new ImageIcon(image);
            }catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Could not find resource");
            return null;
        }
    }


