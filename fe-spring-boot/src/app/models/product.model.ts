import {ITag} from "./tag.model";
import {IAuthor} from "./author.model";
import {ICategory} from "./category.model";

export interface IFilterPost {
  page: number;
  size: number;
  categoryId: string;
}

export interface IPost {
  id: number
  title: string
  subTitle: string
  contents: IContent[]
  thumbnail: string
  createdAt: string
  updatedAt: string
  published: boolean
  tags: ITag[]
  author: IAuthor
  category: ICategory
  authorsOfLike: number[];
}

export interface IContent {
  id: number
  type: string
  value: string | string[]
  position: number
  postId: number
}

export interface IFormPost {
  id: number
  title: string
  subTitle: string
  contents: IContent[]
  thumbnail: string
  createdAt: string
  updatedAt: string
  published: boolean
  tags: string[]
  authorId: string
  categoryId: string
}
