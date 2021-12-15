package ru.netology

import java.lang.RuntimeException

class PostNotFoundException(message: String) : RuntimeException(message)

class WrongReasonException(message: String) : RuntimeException(message)

object WallService {
    private var posts = emptyArray<Post>()
    private var uniqueId = 0
    private var comments = emptyArray<Comment>()
    private var badComments = emptyArray<BadComment>()

    fun add(post: Post): Post {
        uniqueId++
        posts += post.copy(id = uniqueId)
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, postIteration) in posts.withIndex()) {
            if (post.id == postIteration.id) {
                posts[index] = post.copy(
                    id = post.id,
                    text = post.text
                )
                return true
            }
        }
        return false
    }

    fun createComment(comment: Comment): Boolean {
        findById(comment.postId)
            ?: throw PostNotFoundException("Not found post with id ${comment.postId}")

        comments += comment
        return true
    }

    fun reportComment(badComment: BadComment): Boolean {
        findById(badComment.commentId)
            ?: throw PostNotFoundException("Not found post with id ${badComment.commentId}")

        when (badComment.reason) {
            1, 2, 3, 4, 5, 6, 8 -> {
                badComments += badComment
                return true
            }
            else -> throw WrongReasonException("Wrong reason of bad comment ${badComment.reason}")
        }
    }

    private fun findById(idPost: Int): Post? {
        for (postIteration in posts) {
            if (idPost == postIteration.id) {
                return postIteration
            }
        }
        return null
    }
}