import java.lang.RuntimeException

class NoteNotFoundException(message: String) : RuntimeException(message) {
}

fun main() {

    val note1 = Note(
        id = 1,
        title = "Заголовок 1",
        text = "Начало",
        date = 20230423,
        commentsCount = 0,
        readComments = 0,
        viewUrl = null,
        privacyView = null,
        canComment = false,
        textWiki = null
    )
    val note2 = Note(
        id = 2,
        title = "Заголовок 2",
        text = "Продолжение",
        date = 20230423,
        commentsCount = 0,
        readComments = 0,
        viewUrl = null,
        privacyView = null,
        canComment = false,
        textWiki = null
    )
    val note3 = Note(
        id = 3,
        title = "Заголовок 3",
        text = "Конец",
        date = 20230423,
        commentsCount = 0,
        readComments = 0,
        viewUrl = null,
        privacyView = null,
        canComment = false,
        textWiki = null
    )

    NoteService.addNote(note1)
    NoteService.addNote(note2)
    println(NoteService.getNotes())
    NoteService.createComment(1, "Коммент_1", 20230424)
    NoteService.createComment(1, "Коммент_2", 20230425)
    println(NoteService.getNotes())
    println()
    println(NoteService.getComments(1))
    NoteService.deleteComment(1)
    println(NoteService.getNotes())
    println()
    NoteService.restoreComment(1)
    println(NoteService.getNotes())
    NoteService.deleteNote(1)
    println(NoteService.getNotes())
    println(NoteService.getComments(1))

//    println(NoteService.getNoteById(1))
//    NoteService.deleteNote(1)
//    println(NoteService.getNotes())
//    NoteService.editNote(1, "Заметка редакция", "Изменение")
//    println(NoteService.getNotes())

}