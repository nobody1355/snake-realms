# Snake Realms

Snake Realms is the classic Snake game granting the player the ability to choose between different realms. Players can navigate the snake to eat food, grow, earn points and power-ups while choosing from multiple themed realms including Jungle, Space, and Ocean. The game demonstrates clean architecture using JavaFX and several design patterns.

## Features
- Multiple themed realms (Jungle, Space, Ocean)  
- Score tracking with persistent high scores (works in Maven run)  
- Dynamic snake powers using the Decorator pattern:
  - Speed Boost
  - Intangibility
  - Score Multiplier
- Responsive JavaFX UI  
- Demonstrates design patterns:
  - **Singleton**: `GameController`
  - **Abstract Factory**: `RealmFactory` and realm-specific factories
  - **Observer**: `Scoreboard` updates automatically
  - **Decorator**: Snake powers  

## Installation
1. Clone the repository:
```bash
git clone https://github.com/<nobody1355>/SnakeRealms.git
```

2. Open the project in an IDE like VSCode or IntelliJ IDEA.

3. Run the game using Maven:

```bash
mvn clean javafx:run
```

## Controls

* Arrow Keys: Move the snake
* Spacebar: Pause/Resume game

## Notes

High score works reliably when running via Maven. The installed EXE may have issues with saving high scores due to system paths.
This project is built with Java 17 and JavaFX 21.

## License

MIT License