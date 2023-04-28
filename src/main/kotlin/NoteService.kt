object NoteService {
    private var notes = mutableListOf<Note>()
    private var comments = mutableListOf<Comment>()
    private var idNote = 1
    private var idComment = 1

    fun <T : ClassWithId> addElem(elem: T): Int {
        if (elem is Note) {
            notes += elem
            idNote++
            return elem.id
        } else if (elem is Comment) {
            val comment = Comment(idComment, elem.noteId, elem.date, elem.text)
            for (item in notes.listIterator()) {
                if (item.id == elem.noteId) {
                    comments += comment
                    idComment++
                    item.commentsCount++
                }
            }
            return comment.id
        }
        return -1
    }

//    fun createComment(noteId: Int, message: String, date: Int): Int {
//        for (item in notes.listIterator()) {
//            if (item.id == noteId) {
//                val comment = Comment(idComment, noteId, date, message)
//                comments.add(comment)
//                idComment++
//                item.commentsCount++
//                return comment.id
//            }
//        }
//        throw NoteNotFoundException("No note with id = $noteId")
//    }

    fun deleteNote(noteId: Int): Int {
        val toRemove = ArrayList<Comment>()
        for (item in notes.listIterator()) {
            if (item.id == noteId) {
                notes.removeAt(item.id - 1)
                for (item in comments.listIterator()) {
                    if (item.noteId == noteId) {
                        toRemove.add(item)
                    }
                }
                comments.removeAll(toRemove)
                return 1
            }
        }
        throw ElemNotFoundException("No element with id = $noteId")
    }

    fun deleteComment(commentId: Int): Int {
        for (item in comments.listIterator()) {
            if (item.id == commentId && item.visibility) {
                item.visibility = false
                //notes[item.noteId - 1].commentsCount--
                getNoteById(item.noteId).commentsCount--
                return 1
            }
        }
        throw ElemNotFoundException("No element with id = $commentId")
    }


//    fun <T : ClassWithId> edit(comNote: T): Int {
//        if (comNote is Note) {
//            for (item in notes.listIterator()) {
//                if (item.id == comNote.id) {
//                    item.title = comNote.title
//                    item.text = comNote.text
//                    return 1
//                }
//            }
//        } else if (comNote is Comment) {
//            for (item in comments.listIterator()) {
//                if (item.id == comNote.id && item.visibility) {
//                    item.text = comNote.text
//                    return 1
//                }
//            }
//        }
//        throw NoteNotFoundException("No note(comment) with id = ${comNote.id}")
//    }

    fun editNote(noteId: Int, title: String, text: String): Int {
        for (item in notes.listIterator()) {
            if (item.id == noteId) {
                item.title = title
                item.text = text
                return 1
            }
        }
        throw ElemNotFoundException("No element with id = $noteId")
    }

    fun editComment(commentId: Int, message: String): Int {
        for (item in comments.listIterator()) {
            if (item.id == commentId && item.visibility) {
                item.text = message
                return 1
            }
        }
        throw ElemNotFoundException("No element with id = $commentId")
    }

    fun getElem(id: Int?): MutableList<out ClassWithId> {
        return if (id == null) {
            notes
        } else {
            val result = ArrayList<Comment>()
            for (item in comments.listIterator()) {
                if (item.noteId == id && item.visibility) {
                    result += item
                }
            }
            result
        }
    }

//    fun getNotes(): MutableList<Note> {
//        return notes
//    }

    fun getNoteById(noteId: Int): Note {
        for (i in notes.indices) {
            if (notes[i].id == noteId)
                return notes[i]
        }
        throw ElemNotFoundException("No element with id = $noteId")
    }

//    fun getComments(noteId: Int): ArrayList<Comment> {
//        val result = ArrayList<Comment>()
//        for (item in comments.listIterator()) {
//            if (item.noteId == noteId && item.visibility) {
//                result.add(item)
//            }
//        }
//        return result
//    }

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