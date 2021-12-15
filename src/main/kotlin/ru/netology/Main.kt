package ru.netology

fun main() {
    val firstPost = addPost("First post")
    printPost(firstPost)

    val secondPost = addPost("Second post")
    printPost(secondPost)

    val updatedPost = addPostNoIdUnique(1, "Updated post")
    if (updatePost(updatedPost)) {
        println("Updated post:")
        printPost(updatedPost)
    }

//    printPostSource(updatedPost)
//
//    printInfoSealedClasses()

    val comment = newComment(1, 2, "test post", 1)
    if (WallService.createComment(comment)) {
        println("Comment successfully created")
    }

    val badComment = newBadComment(1, 2, 11)
    if (WallService.reportComment(badComment)) {
        println("The report about bad comment successfully created")
    }
}

fun newComment(idComment: Int, postIdComment: Int, textComment: String, fromIdComment: Int): Comment {
    return Comment(
        id = idComment,
        postId = postIdComment,
        text = textComment,
        fromId = fromIdComment
    )
}

fun newBadComment(ownerIdBadComment: Int, commentIdBadComment: Int, reasonBadComment: Int): BadComment {
    return BadComment(
        ownerId = ownerIdBadComment,
        commentId = commentIdBadComment,
        reason = reasonBadComment
        )
}

fun addPost(textPost: String): Post {
    val post = Post(
        id = 0,
        text = textPost,
        comments = Comments(0, canPost = false, groupsCanPost = false, canClose = false, canOpen = false),
        copyright = Copyright(0, "", "Empty", ""),
        likes = Likes(0, userLikes = false, canLike = false, canPublish = false),
        reposts = Reposts(0, false),
        views = Views(0),
        donut = Donut(false, 0, Placeholder(), false, "")
    )

    return WallService.add(post)
}

fun addPostNoIdUnique(idPost: Int, textPost: String): Post {
    return Post(
        id = idPost,
        text = textPost,
        comments = Comments(0, canPost = false, groupsCanPost = false, canClose = false, canOpen = false),
        copyright = Copyright(0, "", "Empty", ""),
        likes = Likes(0, userLikes = false, canLike = false, canPublish = false),
        reposts = Reposts(0, false),
        views = Views(0),
        donut = Donut(false, 0, Placeholder(), false, "")
    )
}

fun printPost(post: Post) {
    val postID = post.id
    val postText = post.text
    println("PostID = $postID")
    println("PostText = $postText")
}

fun updatePost(post: Post): Boolean {
    return WallService.update(post)
}

fun printPostSource(post: Post) {
    val postSourceType = post.postSource?.type ?: "no PostSource"
    println(postSourceType)
}

fun printInfoSealedClasses() {
    val attachmentGraffiti: Attachment = GraffitiAttachment("stuff")
    println("Type for GraffitiAttachment: ${attachmentGraffiti.type}")

    val attachmentAudio: Attachment = AudioAttachment("stuff")
    println("Type for AudioAttachment: ${attachmentAudio.type}")

    val attachmentPhoto: Attachment = PhotoAttachment("stuff")
    println("Type for PhotoAttachment: ${attachmentPhoto.type}")

    val attachmentPostedPhoto: Attachment = PostedPhotoAttachment("stuff")
    println("Type for PostedPhotoAttachment: ${attachmentPostedPhoto.type}")

    val attachmentPage: Attachment = PageAttachment("stuff")
    println("Type for PageAttachment: ${attachmentPage.type}")
}