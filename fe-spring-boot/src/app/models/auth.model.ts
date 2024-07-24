export interface ILogin {
  email: string;
  password: string;
}

export interface ILoginResponse {
  token: string;
}

export interface ITokeInfo {
  iss: string
  sub: string
  userInfo: IUserInfo
  exp: number
  iat: number
}

export interface IUserInfo {
  id: number
  email: string
  password: string
  firstName: string
  lastName: string
  posts: any[]
  likeOfPosts: any[]
  createdAt: string
  updatedAt: string
}
