🌤️ Weather Client — Java OpenWeather API Console App

A simple yet powerful Java console application that retrieves real-time weather data
from the OpenWeatherMap API
.
Built using Java 25, Maven, and Gson.

🚀 Features

✅ Fetch current weather by city name

✅ Supports English or Russian localization

✅ Displays temperature, humidity, wind speed, and description

✅ Robust error handling for invalid inputs or network issues

✅ Docker-ready & Maven-compatible

✅ Lightweight CLI interface — no GUI required

🧩 Tech Stack
Component	Description
Language	Java 25 (JDK 25)
Build Tool	Apache Maven 3.9+
HTTP Client	Java 11+ built-in HttpClient
JSON Parsing	Gson

API Source	OpenWeatherMap
📁 Project Structure
weather_client/
├── src/
│   ├── main/
│   │   ├── java/org/example/App.java        # Main program
│   │   └── resources/config.properties      # Stores API key
│   └── test/                                # Optional tests
├── pom.xml                                  # Maven dependencies & build config
├── Dockerfile                               # Docker container definition
└── README.md                                # This documentation 😎

⚙️ Installation & Setup
1️⃣ Clone the repository
git clone https://github.com/your-username/weather-client.git
cd weather-client

2️⃣ Configure your API key

Create the file:

src/main/resources/config.properties


Add your key:

api.key=YOUR_OPENWEATHER_API_KEY


🔑 You can obtain your key here: https://home.openweathermap.org/api_keys

🧱 Build and Run (Maven)
▶️ Compile the project
mvn clean compile

▶️ Run the application
mvn exec:java -Dexec.mainClass=org.example.App

▶️ Or build a JAR
mvn package
java -jar target/weather-client-1.0-SNAPSHOT.jar

🐳 Run with Docker
🏗️ Build the image
docker build -t weather-client .

▶️ Run the container
docker run -it --rm weather-client


📘 The image automatically copies your config.properties file
and runs the console application inside a JDK 25 environment.

💻 Usage Example
🌤 Welcome to the Weather Client!
Enter a command (type 'help' for assistance):
> help

Available commands:
weather <city> — get weather by city name
help — show this help message
quit — exit the program
Example: weather London

> weather Berlin

🌍 Current weather in Berlin:
Temperature: 12.3°C
Description: scattered clouds
Humidity: 78%
Wind speed: 4.5 m/s

🧰 Dependencies (from pom.xml)
<dependencies>
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.11.0</version>
    </dependency>
    <dependency>
        <groupId>org.apache.httpcomponents.client5</groupId>
        <artifactId>httpclient5</artifactId>
        <version>5.4.1</version>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.11.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>

🧠 Common Issues & Solutions
Problem	Cause	Solution
config.properties not found	File missing or misplaced	Place it in src/main/resources/
API key invalid	Wrong key or typo	Check your key at OpenWeatherMap

java.lang.IllegalStateException: Scanner closed	Scanner was closed too early	Remove scanner.close() or use @SuppressWarnings("resource")
Resource leak: 'scanner' is never closed	IDE warning	Safe to ignore for System.in, or use @SuppressWarnings("resource")
🧑‍💻 Author

Roman Yakushev
📚 Student of Applied Mathematics & Computer Science
🎓 Immanuel Kant Baltic Federal University
💪 Leadership, teamwork, and software engineering experience

📧 romanyakushev4@gmail.com

MIT License © 2025 Roman Yakushev

You are free to use, modify, and distribute this project with attribution.

⭐ Support the Project

If you like this project, give it a ⭐ on GitHub — it really helps!

git add README.md && git commit -m "Add awesome README" && git push


“Clean code always looks like it was written by someone who cares.” — Robert C. Martin
