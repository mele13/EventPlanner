import express from 'express'
import { UserService } from '../services/userService'
// import { auth } from '../middleware/auth'

const auth = require('../middleware/auth')
const joi = require('@hapi/joi')

const router = express.Router()
const userService = new UserService()

// User validation
const registerValidation = joi.object({
  username: joi.string().min(6).max(255).required(),
  email: joi.string().min(6).max(255).required().email(),
  password: joi.string().min(6).max(1024).required(),
})

// Get all users
router.get('/', auth, async (_req, res) => {
  const users = await userService.getAllUsers()
  return res.json(users)
})

// Get user by id
router.get('/:id', auth, async (req, res) => {
  const userId = +req.params.id;
  const user = await userService.getUserById(userId);

  if (user) {
    return res.json(user);
  } else {
    return res.status(404).json({ message: 'User not found' });
  }
});

// Delete a user by id
router.delete('/:id', auth, async (req, res) => {
  try {
    const userId = +req.params.id;
    await userService.deleteUser(userId)
    return res.status(200).send('User successfully deleted')
  } catch (e) {
    if (e instanceof Error) {
      return res.status(404).send(e.message)
    } else {
      return res.status(500).send('An unexpected error occurred.')
    }
  }
});

router.post('/register', auth, async (req, res) => {
  // Validate user
  const { validationError } = registerValidation.validate(req.body)
  if (validationError) {
    return res.status(400).json({
      error: validationError.details[0].message
    })
  }

  // Existing email validation
  const existingEmail = await userService.emailExists(req.body.email)
  if (existingEmail) {
    return res.status(400).send('Invalid email')
  }

  try {
    const newUser = await userService.register(req.body)
    return res.json({
      error: null,
      data: newUser
    })
    // res.status(201).send('Successfully created a new user')
  } catch (e) {
    if (e instanceof Error) {
      return res.status(400).send(e.message)
    }
    return res.status(500).send('An unexpected error occurred.')
  }
});

// User login
router.post('/login', auth, async (req, res) => {
  try {
    const foundUser = await userService.login(req.body)
    return res.status(200).send(foundUser)
  } catch (e) {
    if (e instanceof Error) {
      return res.status(400).send(e.message)
    }
    return res.status(500).send('An unexpected error occurred.')
  }
});

export default router;
