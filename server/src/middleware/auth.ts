import jwt, { Secret, JwtPayload } from 'jsonwebtoken'
import { Request, Response, NextFunction } from 'express'

export const SECRET_KEY: Secret = process.env.SECRET_KEY || 'abece';
// export const SECRET_KEY: Secret = 'aabbccddee';

export interface CustomRequest extends Request {
  token: string | JwtPayload;
}

export interface CustomRequest extends Request {
 token: string | JwtPayload
}

export const auth = async (req: Request, res: Response, next: NextFunction) => {
  try {
    const token = req.header('Authorization')?.replace('Bearer ', '')
    
    console.log('Token received ', token)
    if (!token) {
      console.log("no token")
      throw new Error()
    }
    console.log("si token")
    const decoded = jwt.verify(token, SECRET_KEY);
    (req as CustomRequest).token = decoded;

    console.log("antes next")
    next()
    console.log("despues next")
  } catch (err) {
    res.status(401).send('Please authenticate')
  }
}
