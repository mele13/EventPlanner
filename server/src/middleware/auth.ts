import { NextFunction } from "express";

const jwt = require("jsonwebtoken");

module.exports = (req: any, res: any, next: NextFunction) => {
  var token = req.headers.authorization.split(' ')[1]
  console.log("token ", token)
  if (!token) {
    return res.status(401).send('Access denied')
  }

  try {
    console.log("before verify", token, "aa", process.env.SECRET_KEY)
    const verified = jwt.verify(token, process.env.SECRET_KEY)
    console.log("after verify")
    req.user = verified
    console.log("last verify")

    next()
    console.log("next verify")
    
  } catch (e) {
    console.error('Error during token verification:', e);
    return res.status(400).send('Invalid token')
  }
}
