import { setActivePinia, createPinia } from 'pinia';
import { describe, it, beforeEach, afterEach, expect, vi } from 'vitest';
import { useReportSummaryStore } from '~/store/reportSummary';
import { reportSummaryService } from '~/services';
import { useNotificationStore } from '~/store/notification';

vi.mock('~/services', () => ({
  reportSummaryService: {
    serviceGetReportSummary: vi.fn(),
  },
}));


describe('useReportSummaryStore', () => {
  setActivePinia(createPinia());

  const notificationStore = useNotificationStore();
  const addNotificationSpy = vi.spyOn(notificationStore, 'addNotification');

  const mockStore = useReportSummaryStore();

  beforeEach(() => {
    setActivePinia(createPinia());
    mockStore.$reset();

    notificationStore.$reset();
    addNotificationSpy.mockReset();
  });


  afterAll(() => {
    addNotificationSpy.mockRestore();
  });

  it('should fetch report summary successfully', async () => {
    const mockResponse = {
      data: {
        data: [{ id: 1, name: 'Report 1' }],
      },
    };
    reportSummaryService.serviceGetReportSummary.mockResolvedValue(mockResponse);

    await mockStore.fetchReportSummary(1);

    expect(mockStore.processingReportSummary).toBe(false);
    expect(mockStore.reportSummaryData).toEqual([{ id: 1, name: 'Report 1' }]);
    expect(addNotificationSpy).not.toHaveBeenCalled();
  });

  it('should handle error when fetching report summary', async () => {
    const mockError = {
      response: {
        data: {
          message: 'Error',
          status: 500,
        },
      },
    };
    reportSummaryService.serviceGetReportSummary.mockRejectedValue(mockError);

    await mockStore.fetchReportSummary(1);

    expect(mockStore.processingReportSummary).toBe(false);
    expect(mockStore.reportSummaryData).toEqual([]);
    expect(addNotificationSpy).toHaveBeenCalledTimes(1);
    expect(addNotificationSpy).toHaveBeenCalledWith({
      text: 'Error',
      type: 'error',
    });
  });
});