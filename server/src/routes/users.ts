import express from 'express'
import { UserService } from '../services/userService'
// import { auth } from '../middleware/auth'

const router = express.Router()
const userService = new UserService()

// Get all users
// router.get('/', auth, async (_req, res) => {
router.get('/', async (_req, res) => {
  const users = await userService.getAllUsers()
  res.json(users)
})

// Get user by id
router.get('/:id', async (req, res) => {
// router.get('/:id', auth, async (req, res) => {
  const userId = +req.params.id;
  const user = await userService.getUserById(userId);

  if (user) {
    res.json(user);
  } else {
    res.status(404).json({ message: 'User not found' });
  }
});

// Delete a user by id
// router.delete('/:id', auth, async (req, res) => {
router.delete('/:id', async (req, res) => {
  try {
    const userId = +req.params.id;
    await userService.deleteUser(userId);
    res.status(200).send('User successfully deleted');
  } catch (e) {
    if (e instanceof Error) {
      res.status(404).send(e.message);
    } else {
      res.status(500).send('An unexpected error occurred.');
    }
  }
});

// Register a user
router.post('/register', async (req, res) => {
// router.post('/register', auth, async (req, res) => {
  try {
    await userService.register(req.body);
    res.status(201).send('Successfully created a new user');
  } catch (e) {
    if (e instanceof Error) {
      res.status(400).send(e.message)
    }
    res.status(500).send('An unexpected error occurred.')
  }
});

// User login
router.post('/login', async (req, res) => {
// router.post('/login', auth, async (req, res) => {
  try {
    const foundUser = await userService.login(req.body);
    res.status(200).send(foundUser);
  } catch (e) {
    if (e instanceof Error) {
      res.status(400).send(e.message)
    }
    res.status(500).send('An unexpected error occurred.')
  }
});

export default router;
