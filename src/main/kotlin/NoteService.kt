object NoteService {
    private var notes = ArrayList<Note>()
    private var comments = ArrayList<Comment>()
    private var idNote = 1
    private var idComment = 1

    fun addNote(note: Note): Int {
        notes.add(note)
        idNote++
        return note.id
    }

    fun createComment(noteId: Int, message: String, date: Int): Int {
        for (item in notes.listIterator()) {
            if (item.id == noteId) {
                val comment = Comment(idComment, noteId, date, message)
                comments.add(comment)
                idComment++
                item.commentsCount++
                return comment.id
            }
        }
        throw NoteNotFoundException("No note with id = $noteId")
    }

    fun deleteNote(noteId: Int): Int {
        val toRemove = ArrayList<Comment>()
        for (item in notes.listIterator()) {
            if (item.id == noteId) {
                notes.removeAt(noteId - 1)
                for (item in comments.listIterator()) {
                    if (item.noteId == noteId) {
                        toRemove.add(item)
                    }
                }
                comments.removeAll(toRemove)
                return 1
            }
        }
        throw NoteNotFoundException("No note with id = $noteId")
    }

    fun deleteComment(commentId: Int): Boolean {
        for (item in comments.listIterator()) {
            if (item.id == commentId && item.visibility) {
                item.visibility = false
                notes[item.noteId - 1].commentsCount--
                return true
            }
        }
        return false
    }

    fun editNote(noteId: Int, title: String, text: String): Int {
        for (item in notes.listIterator()) {
            if (item.id == noteId) {
                item.title = title
                item.text = text
                return 1
            }
        }
        throw NoteNotFoundException("No note with id = $noteId")
    }

    fun editComment(commentId: Int, message: String): Boolean {
        for (item in comments.listIterator()) {
            if (item.id == commentId && item.visibility) {
                item.text = message
                return true
            }
        }
        return false
    }

    fun getNotes(): MutableList<Note> {
        return notes
    }

    fun getNoteById(noteId: Int): Note {
        for (i in notes.indices) {
            if (notes[i].id == noteId)
                return notes[i]
        }
        throw NoteNotFoundException("No note with id = $noteId")
    }

    fun getComments(noteId: Int): ArrayList<Comment> {
        val result = ArrayList<Comment>()
        for (item in comments.listIterator()) {
            if (item.noteId == noteId && item.visibility) {
                result.add(item)
            }
        }
        return result
    }

    fun restoreComment(commentId: Int): Boolean {
        for (item in comments.listIterator()) {
            if (item.id == commentId) {
                item.visibility = true
                notes[item.noteId - 1].commentsCount++
                return true
            }
        }
        return false
    }

    fun clear() {
        notes.removeAll(notes)
        comments.removeAll(comments)
        idNote = 1
        idComment = 1
    }
}