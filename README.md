# The-Ducks
LaunchCode - 2024 Web Development
Anastacia Brooks, Lorri Lanig, Roger Thomas, Vaishnavi Devi B D

Project Name: GitArtsy
Description
GitArtsy is a dynamic web platform designed for artists and art enthusiasts. It enables artists to showcase their artwork and receive commissions from patrons who would like to hire them. The application integrates features like artwork uploads, artist profiles, a like button, notifications bar, and an image search functionality to enhance user interaction and accessibility.
Features
Artwork Upload: Artists can upload images of their artwork, providing titles, descriptions, and tags to enhance discoverability.
Artist Profiles: Users can create and manage detailed profiles that include biographical information, contact details, and a portfolio of their artworks.
Image Search: Users can search for artworks based on various criteria including title, tags, and descriptions.
Commissioning Artwork: Patrons can directly request commissions from artists through an intuitive interface.
Responsive Design: Ensures a seamless viewing experience across all devices.
Technologies
Frontend: React.js, Bootstrap for responsive design.
Backend: Spring Boot, providing RESTful services.
Database: MySQL for data management.
APIs: Chicago Institute of Art Open Source API : https://api.artic.edu/api/v1/artworks/

Installation
Prerequisites
Node.js
npm 
Java JDK 17+
MySQL




Setting up the Development Environment

Clone the repository
Copy code into terminal.
git clone https://github.com/ALLHUBS-Jan-2024-Liftoff/The-Ducks.git
cd The-Ducks
Frontend Setup
Copy code
cd frontend
npm install
npm start or npm run dev
This will start the React development server on http://localhost:5173.
Backend Setup
Copy code
cd ../backend
./mvnw spring-boot:run
This will start the Spring Boot server on http://localhost:8082.
Database Configuration
Ensure MySQL is running on your machine.
Create a database named gitartsy and configure the application properties accordingly in backend/src/main/resources/application.properties.

Usage
Once both servers are running, navigate to http://localhost:5173 in your web browser to view and interact with the application.






Contributing
Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are greatly appreciated.
Fork the Project
Create your Feature Branch (git checkout -b feature/AmazingFeature)
Commit your Changes (git commit -m 'Add some AmazingFeature')
Push to the Branch (git push origin feature/AmazingFeature)
Open a Pull Request
License
Distributed under the MIT License. See LICENSE for more information.
?????
Contact
Your Name - your.email@example.com
Project Link: GitArtsy on GitHub
