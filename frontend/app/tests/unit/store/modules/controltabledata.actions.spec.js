import { setActivePinia, createPinia } from 'pinia';
import { beforeEach, describe, expect, it, vi } from 'vitest';
import { controlTableService } from '~/services';
import { useControlTableDataStore } from '~/store/controltabledata';
import { useNotificationStore } from '~/store/notification';

describe('Control Table Data actions', () => {
    setActivePinia(createPinia());
    
    const notificationStore = useNotificationStore();
    const addNotificationSpy = vi.spyOn(notificationStore, 'addNotification');

    const mockStore = useControlTableDataStore();
    const servicePutLoadToPlrlSpy = vi.spyOn(controlTableService, 'servicePutLoadToPlrl');

    const serviceUpdateApproveControlTableSpy = vi.spyOn(controlTableService, 'serviceUpdateApproveControlTable');
  
    const serviceAddControlTableSpy = vi.spyOn(controlTableService, 'serviceAddControlTable');

    const serviceGetControlTableByIdSpy = vi.spyOn(controlTableService, 'serviceGetControlTableById');

    const serviceGetAllControlTableSpy = vi.spyOn(controlTableService, 'serviceGetAllControlTable');
    beforeEach(() => {
        mockStore.$reset();
        notificationStore.$reset();
        addNotificationSpy.mockReset();

        servicePutLoadToPlrlSpy.mockReset();
        serviceUpdateApproveControlTableSpy.mockReset();
        serviceAddControlTableSpy.mockReset();
        serviceGetControlTableByIdSpy.mockReset();
        serviceGetAllControlTableSpy.mockReset();
      });
    
      afterAll(() => {
        addNotificationSpy.mockRestore();
        servicePutLoadToPlrlSpy.mockRestore();
        serviceUpdateApproveControlTableSpy.mockRestore();
        serviceAddControlTableSpy.mockRestore();
        serviceGetControlTableByIdSpy.mockRestore();
        serviceGetAllControlTableSpy.mockRestore();
      });

    describe('updateLoadToPlrl action scenarios:', () => {
        it('updateLoadToPlrl should dispatch to notifications/addNotification on an error', async () => {
            controlTableService.servicePutLoadToPlrl.mockRejectedValue('');
            await mockStore.updateLoadToPlrl(1);

            expect(addNotificationSpy).toHaveBeenCalledTimes(1);
            expect(addNotificationSpy).toHaveBeenCalledWith({
            type: 'error',
            text: 'Something went wrong',
            });
        });

        it('updateLoadToPlrl should dispatch to notifications/addNotification on an warning', async () => {
            controlTableService.servicePutLoadToPlrl.mockResolvedValue({data: {
                "status": "error",
                "statusCode": 404,
                "message": "Missing approval from Reg Admin to load to PLR.",
                "totalItems": null,
                "data": "[]"
            }});
            await mockStore.updateLoadToPlrl(2);

            expect(addNotificationSpy).toHaveBeenCalledTimes(1);
            expect(addNotificationSpy).toHaveBeenCalledWith({
            type: 'warning',
            text: 'Missing approval from Reg Admin to load to PLR.',
            });
        });

        it('updateLoadToPlrl should dispatch to notifications/addNotification on an success', async () => {
            controlTableService.servicePutLoadToPlrl.mockResolvedValue({data: {
                "status": "success",
                "statusCode": 200,
                "message": "PLR load process started!",
                "totalItems": null,
                "data": {
                    "id": 1,
                    "fileName": "Example.xlsx",
                }
            }});
            await mockStore.updateLoadToPlrl(2);

            expect(addNotificationSpy).toHaveBeenCalledTimes(1);
            expect(addNotificationSpy).toHaveBeenCalledWith({
            type: 'success',
            text: 'PLR load process started!',
            });
            expect(mockStore.singleControlTableData).toEqual({ id: 1, fileName: 'Example.xlsx'});
        });
    });

    describe('fetchUpdateApproveControlTable action scenarios:', () => {
        it('fetchUpdateApproveControlTable should dispatch to notifications/addNotification on an error', async () => {
            controlTableService.serviceUpdateApproveControlTable.mockRejectedValue('');
            await mockStore.fetchUpdateApproveControlTable(1);

            expect(addNotificationSpy).toHaveBeenCalledTimes(1);
            expect(addNotificationSpy).toHaveBeenCalledWith({
            type: 'error',
            text: 'Something went wrong',
            });
        });

        it('fetchUpdateApproveControlTable should dispatch to notifications/addNotification on an warning', async () => {
            controlTableService.serviceUpdateApproveControlTable.mockResolvedValue({data: {
                "status": "error",
                "statusCode": 404,
                "message": "Missing approval from Reg Admin to load to PLR.",
                "totalItems": null,
                "data": "[]"
            }});
            await mockStore.fetchUpdateApproveControlTable(3);

            expect(addNotificationSpy).toHaveBeenCalledTimes(1);
            expect(addNotificationSpy).toHaveBeenCalledWith({
            type: 'error',
            text: 'Missing approval from Reg Admin to load to PLR.',
            });
        });

        it('fetchUpdateApproveControlTable should dispatch to notifications/addNotification on an success', async () => {
            controlTableService.serviceUpdateApproveControlTable.mockResolvedValue({data: {
                "status": "success",
                "statusCode": 200,
                "message": "PLR load process started!",
                "totalItems": null,
                "data": {
                    "id": 1,
                    "fileName": "Example.xlsx",
                }
            }});
            await mockStore.fetchUpdateApproveControlTable(2);

            expect(addNotificationSpy).toHaveBeenCalledTimes(1);
            expect(addNotificationSpy).toHaveBeenCalledWith({
            type: 'success',
            text: 'PLR load process started!',
            });
            expect(mockStore.singleControlTableData).toEqual({ id: 1, fileName: 'Example.xlsx'});
        });
    });

    describe('fetchAddControlTable action scenarios:', () => {
        it('fetchAddControlTable should successfully add a control table', async () => {
            const mockData = { id: 1, name: 'Test Control Table' };
            controlTableService.serviceAddControlTable.mockResolvedValue({ data: mockData });
            await mockStore.fetchAddControlTable(mockData);
            
            expect(controlTableService.serviceAddControlTable).toHaveBeenCalledWith(mockData);
            expect(mockStore.singleControlTableData).toEqual(mockData);
        });
    });

    describe('fetchGetControlTableById action scenarios:', () => {
        it('fetchGetControlTableById should successfully add a control table', async () => {
            const mockData = { id: 1, name: 'Test Control Tabless' };
            controlTableService.serviceGetControlTableById.mockResolvedValue({data: {
                "status": "success",
                "statusCode": 200,
                "message": "",
                "data": mockData }});
            await mockStore.fetchGetControlTableById(1);
            expect(controlTableService.serviceGetControlTableById).toHaveBeenCalledWith(1);

            expect(mockStore.singleControlTableData).toEqual(mockData);
        });

        it('fetchGetControlTableById should dispatch to notifications/addNotification on an error', async () => {
            const mockData = { id: 1, name: 'Test Control Tabless' };
            controlTableService.serviceGetControlTableById.mockResolvedValue({data: {
                "status": "error",
                "message": "" }});
            await mockStore.fetchGetControlTableById(1);
            expect(controlTableService.serviceGetControlTableById).toHaveBeenCalledWith(1);
            
            
            expect(addNotificationSpy).toHaveBeenCalledTimes(1);
            expect(addNotificationSpy).toHaveBeenCalledWith({
            type: 'error',
            text: 'No record found.',
            });
            expect(mockStore.singleControlTableData).toEqual([]);
        });
    });

    describe('fetchGetAllControlTable action scenarios:', () => {
        it('fetchGetAllControlTable should successfully add a control table', async () => {
            const mockData = { id: 1, name: 'Test Control Tabless' };
            controlTableService.serviceGetAllControlTable.mockResolvedValue({data: {
                "status": "success",
                "statusCode": 200,
                "message": "",
                "data": [mockData] }});
            await mockStore.fetchGetAllControlTable();
            expect(controlTableService.serviceGetAllControlTable).toHaveBeenCalled();
            expect(mockStore.allControlTableData).toContainEqual(mockData);
        });

        it('fetchGetAllControlTable should dispatch to notifications/addNotification on an warning', async () => {
            const mockData = { id: 1, name: 'Test Control Tabless' };
            controlTableService.serviceGetAllControlTable.mockResolvedValue({data: {
                "status": "error",
                "data": [] }});
            await mockStore.fetchGetAllControlTable();
            expect(controlTableService.serviceGetAllControlTable).toHaveBeenCalled();
            expect(addNotificationSpy).toHaveBeenCalledTimes(1);
            expect(addNotificationSpy).toHaveBeenCalledWith({
            type: 'error',
            text: 'No record found.',
            });
            expect(mockStore.allControlTableData).toEqual([]);
        });

        it('fetchGetAllControlTable should dispatch to notifications/addNotification on an error', async () => {
            controlTableService.serviceGetAllControlTable.mockResolvedValue({data: {
                "status": "error",
                "message": "" }});
            await mockStore.fetchGetAllControlTable();
            expect(controlTableService.serviceGetAllControlTable).toHaveBeenCalled();
            
            
            expect(addNotificationSpy).toHaveBeenCalledTimes(1);
            expect(addNotificationSpy).toHaveBeenCalledWith({
            type: 'error',
            text: 'No record found.',
            });
            expect(mockStore.allControlTableData).toEqual([]);
        });
    });
});
