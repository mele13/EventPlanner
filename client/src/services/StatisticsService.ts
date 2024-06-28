import { sendRequest } from '@/api/Api';
import type { StatisticsDto } from '@/interfaces/dtos/StatisticsDto';

const STATISTICS_API_URL = '/api/statistics';

export async function getAppStatistics(): Promise<StatisticsDto> {
  return await sendRequest("GET", STATISTICS_API_URL);
}
