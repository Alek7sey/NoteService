open class ClassWithId(open val id: Int)

data class Comment(
    override val id: Int,
    val noteId: Int,
    val date: Int,
    var text: String,
    var visibility: Boolean = true //  false - удален
) : ClassWithId(id)