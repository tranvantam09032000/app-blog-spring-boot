export interface IComment {
  id: string
  fullName: string
  content: string
  createdAt: string
  updatedAt: string
}

export interface IFormComment {
  id: string
  fullName: string
  content: string
  postId: string
}
