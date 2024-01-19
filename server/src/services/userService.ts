import * as bcrypt from 'bcrypt';
import { Repository } from "typeorm";
import { User } from "../models/User";
import { AppDataSource } from "../sql/db";

export class UserService {
  private userRepository: Repository<User>
  private saltRounds: number = 8

  constructor() {
    this.userRepository = AppDataSource.getRepository(User)
  }

  async getAllUsers(): Promise<User[]> {
    return await this.userRepository.find()
  }

  async getUserById(id: number): Promise<User | undefined> {
    return await this.userRepository.findOne({ where: { id } }) ?? undefined;
  }

  async register(userData: Partial<User>): Promise<User> {
    const hashedPassword = await bcrypt.hash(userData.password!, this.saltRounds)
    const newUser = this.userRepository.create({
      ...userData,
      password: hashedPassword,
    })
    return await this.userRepository.save(newUser)
  }

  async login(user: Partial<User>): Promise<User> {
    const { username, password } = user
    const foundUser = await this.userRepository.findOne({ where: { username } })

    if (!foundUser) {
      throw new Error('Incorrect username or password')
    }
    
    const isMatch = await bcrypt.compare(password!, foundUser.password)

    if (isMatch) {
      return foundUser;
    } else {
      throw new Error('Incorrect username or password')
    }
  }
}
