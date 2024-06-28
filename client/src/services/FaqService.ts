import { sendRequest } from "@/api/Api";
import type EventFaqDto from "@/interfaces/dtos/FaqDto";
import type { AnswerFaqDto, QuestionFaqDto } from "@/interfaces/dtos/FaqDto";

const ANSWER_FAQ_API_URL = "/api/answer-faqs";
const QUESTION_FAQ_API_URL = "/api/faqs";
const EVENT_FAQ_API_URL = "/api/event-faqs";

export const AnswerFAQService = {
  async getAll(): Promise<AnswerFaqDto[]> {
    return await sendRequest("GET", ANSWER_FAQ_API_URL);
  },

  async getById(id: number): Promise<AnswerFaqDto> {
    return await sendRequest("GET", `${ANSWER_FAQ_API_URL}/${id}`);
  },

  async createAnswerFAQ(request: AnswerFaqDto): Promise<AnswerFaqDto> {
    return await sendRequest("POST", ANSWER_FAQ_API_URL, request);
  },

  async updateAnswerFAQ(request: AnswerFaqDto): Promise<AnswerFaqDto> {
    return await sendRequest("PUT", ANSWER_FAQ_API_URL, request);
  },

  async deleteAnswerFAQ(id: number): Promise<void> {
    await sendRequest("DELETE", `${ANSWER_FAQ_API_URL}/${id}`);
  },
};

export const QuestionFAQService = {
  async getAll(): Promise<QuestionFaqDto[]> {
    return await sendRequest("GET", QUESTION_FAQ_API_URL);
  },

  async getById(id: number): Promise<QuestionFaqDto> {
    return await sendRequest("GET", `${QUESTION_FAQ_API_URL}/${id}`);
  },

  async createQuestionFAQ(request: QuestionFaqDto): Promise<QuestionFaqDto> {
    return await sendRequest("POST", QUESTION_FAQ_API_URL, request);
  },

  async updateQuestionFAQ(request: QuestionFaqDto): Promise<QuestionFaqDto> {
    return await sendRequest("PUT", QUESTION_FAQ_API_URL, request);
  },

  async deleteQuestionFAQ(id: number): Promise<void> {
    await sendRequest("DELETE", `${QUESTION_FAQ_API_URL}/${id}`);
  },
};

export const EventFaqService = {
  async getAll(): Promise<EventFaqDto[]> {
    return await sendRequest("GET", EVENT_FAQ_API_URL);
  },

  async getById(id: number): Promise<EventFaqDto> {
    return await sendRequest("GET", `${EVENT_FAQ_API_URL}/${id}`);
  },

  async createEventFaq(request: EventFaqDto): Promise<EventFaqDto> {
    return await sendRequest("POST", EVENT_FAQ_API_URL, request);
  },

  async updateEventFaq(request: EventFaqDto): Promise<EventFaqDto> {
    return await sendRequest("PUT", EVENT_FAQ_API_URL, request);
  },

  async deleteEventFaq(id: number): Promise<void> {
    await sendRequest("DELETE", `${EVENT_FAQ_API_URL}/${id}`);
  },
};
