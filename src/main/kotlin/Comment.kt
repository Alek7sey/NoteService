data class Comment(
    var id: Int,
    //val userId: Int,
    val noteId: Int,
    val date: Int,
    var text: String,
    var visibility: Boolean = true //  false - удален
)