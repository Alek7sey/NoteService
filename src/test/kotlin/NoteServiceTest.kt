import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class NoteServiceTest {
    private val note1 = Note(
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
    private val note2 = Note(
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
    private val note3 = Note(
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

    @Before
    fun clearBeforeTest() {
        NoteService.clear()
    }

    @Test
    fun addNote() {
        val result = NoteService.addNote(note1)
        kotlin.test.assertEquals(1, result)
    }

    @Test
    fun createComment() {
        NoteService.addNote(note1)
        val result = NoteService.createComment(1, "Коммент_1", 20230424)
        kotlin.test.assertEquals(1, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun shouldThrowComment() {
        NoteService.addNote(note1)
        NoteService.addNote(note2)
        NoteService.createComment(5, "Коммент_1", 20230424)
    }

    @Test
    fun deleteNote() {
        val result = NoteService.addNote(note1)
        NoteService.addNote(note2)
        NoteService.deleteNote(2)
        kotlin.test.assertEquals(1, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun shouldThrowDeleteNode() {
        NoteService.addNote(note1)
        NoteService.addNote(note2)
        NoteService.deleteNote(5)
    }

    @Test
    fun deleteCommentTrue() {
        NoteService.addNote(note1)
        NoteService.createComment(1, "Коммент_1", 20230424)
        val result = NoteService.deleteComment(1)
        kotlin.test.assertTrue(result)

    }

    @Test
    fun deleteCommentFalse() {
        NoteService.addNote(note1)
        NoteService.createComment(1, "Коммент_1", 20230424)
        val result = NoteService.deleteComment(3)
        kotlin.test.assertFalse(result)

    }

    @Test
    fun editNote() {
        NoteService.addNote(note1)
        val result = NoteService.editNote(1, "Title edit", "Text edit")
        kotlin.test.assertEquals(1, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun shouldThrowEditNode() {
        NoteService.addNote(note1)
        NoteService.addNote(note2)
        NoteService.editNote(5, "title", "text")
    }

    @Test
    fun editCommentTrue() {
        NoteService.addNote(note1)
        NoteService.createComment(1, "Коммент_1", 20230424)
        val result = NoteService.editComment(1, "Comment edit")
        kotlin.test.assertTrue(result)
    }

    @Test
    fun editCommentFalse() {
        NoteService.addNote(note1)
        NoteService.createComment(1, "Коммент_1", 20230424)
        val result = NoteService.editComment(3, "Comment edit")
        kotlin.test.assertFalse(result)
    }

    @Test
    fun getNoteById() {
        NoteService.addNote(note1)
        NoteService.addNote(note2)
        val result = NoteService.getNoteById(2)
        kotlin.test.assertEquals(note2, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun shouldThrowGetNoteById() {
        NoteService.addNote(note1)
        NoteService.getNoteById(2)
    }

    @Test
    fun restoreCommentTrue() {
        NoteService.addNote(note1)
        val commentId = NoteService.createComment(1, "Коммент_1", 20230424)
        val result = NoteService.restoreComment(commentId)
        kotlin.test.assertTrue(result)
    }

    @Test
    fun restoreCommentFalse() {
        NoteService.addNote(note1)
        val commentId = NoteService.createComment(note1.id, "Коммент_1", 20230424)
        val result = NoteService.restoreComment(3)
        kotlin.test.assertFalse(result)
    }
}