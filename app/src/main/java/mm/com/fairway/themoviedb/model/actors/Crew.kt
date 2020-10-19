package mm.com.fairway.themoviedb.model.actors

data class Crew(
    val credit_id: String,
    val department: String,
    val gender: Int,
    val id: Int,
    val job: String,
    val name: String,
    val profile_path: String
)