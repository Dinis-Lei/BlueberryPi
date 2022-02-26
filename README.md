<h1 style="text-align: center;">BlueberryPi</h1>



System to monitorize precision agriculture plantations of blueberries.

This project was developed in the context of [Introduction to Software Engineering](https://www.ua.pt/en/uc/12288) curricular unit, part of the Computer Science Bachelor at [Aveiro University](https://www.ua.pt/). It was lectured by [Ilídio Oliveira](https://www.ua.pt/pt/p/10318398) and [José Oliveira](https://www.ua.pt/pt/p/10309676).

It was graded **17**/20 and every contributor had the same grade.

<h2>About the Project</h2>

This system aims to help people involved in precision agriculture plantations to have more control over their plantations, having all the data stored in one place.
Our project collects data from different sensors (temperature, humidity, pH, etc...) in different regions and makes it available on a user-friendly dashboard where the client can check the data, check the history and have real-time alerts when data received is out of range of the normal values.

Since the main focus of this curricular unit was to manage a software engineering development process, we didn't focus on implementing real sensors. For data generation we used a script that generated the data as real as possible.

<h2>Technology Stack</h2>

The system was implemented in SpringBoot, with a MySQL Database. It used RabbitMQ as a message broker to establish communication between SpringBoot and the data generation script, that was developed in Python. The user interface was developed in React.

<h2>Backlog Management</h2>

Our backlog management is done with *ZenHub* and can be accessed [here](https://app.zenhub.com/workspaces/little-berries-619d01ea3f082b001d0e8b7d/board?repos=431140110).

<h2>Specification Report</h2>

Our Specification Report can be found [here](https://docs.google.com/document/d/16-IQ1ZdKy88M73hKmsDsPa9cwICLcbgHG6ycUgUEBbk/edit?usp=sharing).

<h2>Workflow</h2>

* *main* - branch where only finalized and stable features are committed
* *develop* - integration branch for new features

The rest of the branches are going to be created to make new features, following a *feature branching* methodology.

<h2>Link to access the project</h2>

http://192.168.160.210:3001/dashboard
(Only works when the teacher VM is up and running)

<h2>Participants and Team Roles</h2>

* [Afonso Campos](https://github.com/AfonsoCampos971) 100055 - **Product Owner**
* [Dinis Lei](https://github.com/Dinis-Lei) 98452 - **DevOps Master**
* [Isabel Rosário](https://github.com/rospuye) 93343 - **Team Manager**
* [Miguel Ferreira](https://github.com/MiguelF07) 98599 - **Architect**
