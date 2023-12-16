import com.example.gamemaster.model.data.Game
import com.example.gamemaster.model.data.GameDetail
import com.example.gamemaster.model.data.Screenshot
import com.example.gamemaster.model.data.SystemRequirements

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

    val dummyDetail = GameDetail(
        id = 1,
        title = "Assimilated user-facing array",
        thumbnail = "https://www.lorempixel.com/270/318",
        status = "Released",
        shortDescription = "Southern new four authority nothing reflect easy. Tree computer rest class. Test defense tell church north scientist tell.",
        description = "Issue young speech front land civil begin. Election listen movie with visit risk. Price each her miss scene campaign. Price bag mother issue movement. Next same benefit send leave century call.",
        gameUrl = "http://www.harvey.com/",
        genre = "glass",
        platform = "Mobile",
        publisher = "Williams Inc",
        developer = "Johnson LLC",
        releaseDate = "1987-11-25",
        freetogameProfileUrl = "https://www.le.com/",
        minimumSystemRequirements = SystemRequirements(
            os = "party",
            processor = "represent",
            memory = "4 GB",
            graphics = "child",
            storage = "39 GB"
        ),
        screenshots = listOf(
            Screenshot(id = 1697, image = "https://dummyimage.com/324x809"),
            Screenshot(id = 4716, image = "https://www.lorempixel.com/196/218"),
            Screenshot(id = 1886, image = "https://placekitten.com/16/203")
        )
    )



}

// In your composable previews
