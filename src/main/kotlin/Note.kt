data class Note(
    var id: Int,
    //val ownerId: Int,
    var title: String,
    var text: String,
    var date: Int,
    var commentsCount: Int,
    val readComments: Int,
    val viewUrl: String?,
    val privacyView: String?,
    val canComment: Boolean,
    val textWiki: String?
)