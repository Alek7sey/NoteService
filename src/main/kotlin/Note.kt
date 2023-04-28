data class Note(
    override var id: Int,
    var title: String,
    var text: String,
    var date: Int,
    var commentsCount: Int,
    val readComments: Int,
    val viewUrl: String?,
    val privacyView: String?,
    val canComment: Boolean,
    val textWiki: String?
) : ClassWithId(id)




