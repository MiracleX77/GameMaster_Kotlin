import com.example.gamemaster.model.data.Game

// DummyData.kt
object GameData {

    val dummyGames: List<Game> = listOf(
        Game(
            id = 1,
            title = "Space Adventure",
            thumbnail = "https://example.com/space-adventure-thumbnail.jpg",
            shortDescription = "An exciting space exploration game.",
            gameUrl = "https://example.com/space-adventure",
            genre = "Adventure",
            platform = "PC",
            publisher = "Galactic Games",
            developer = "Star Studios",
            releaseDate = "2023-01-15",
            freetogameProfileUrl = "https://example.com/space-adventure-profile"
        ),
        Game(
            id = 2,
            title = "Mystery Island",
            thumbnail = "https://example.com/mystery-island-thumbnail.jpg",
            shortDescription = "Solve mysteries on a mysterious island.",
            gameUrl = "https://example.com/mystery-island",
            genre = "Puzzle",
            platform = "Mobile",
            publisher = "Island Inc.",
            developer = "Puzzle Masters",
            releaseDate = "2022-11-10",
            freetogameProfileUrl = "https://example.com/mystery-island-profile"
        ),
    )
}

// In your composable previews
