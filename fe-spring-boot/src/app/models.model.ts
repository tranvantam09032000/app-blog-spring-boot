export interface IResponse {
  code: number;
  message: string;
  result: any;
}

export interface IPost {
  id: number
  title: string
  subTitle: string
  content: string
  thumbnail: string
  createdAt: string
  updatedAt: string
  published: boolean
  tags: ITag[]
  author: IAuthor
  category: ICategory
}

export interface IFormPost {
  id: number
  title: string
  subTitle: string
  content: string
  thumbnail: string
  createdAt: string
  updatedAt: string
  published: boolean
  tags: string[]
  authorId: string
  categoryId: string
}

export interface ITag {
  id: number
  title: string
}

export interface IAuthor {
  id: number
  email: string
  password: string
  firstName: string
  lastName: string
}

export interface ICategory {
  id: number
  title: string
  subCategory: number
}

export interface IFilterPost {
  page: number;
  size: number;
  categoryId: string;
}
export interface ICategory {
  id: number;
  title: string;
  subCategory: number;
}

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
