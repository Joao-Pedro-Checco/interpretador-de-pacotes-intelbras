package br.com.fulltime.fullarm.core.message.handler.status.full;

import br.com.fulltime.fullarm.core.message.GenericPackageMessage;
import br.com.fulltime.fullarm.core.message.status.full.FullStatusMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FullStatusMessageHandlerImpl implements FullStatusMessageHandler {
    private final FullStatusComponentHandler componentHandler;

    public FullStatusMessageHandlerImpl(FullStatusComponentHandler componentHandler) {
        this.componentHandler = componentHandler;
    }

    @Override
    public List<String> handleMessage(GenericPackageMessage genericPackageMessage) {
        List<String> contentList = new ArrayList<>();
        FullStatusMessage fullStatusMessage = (FullStatusMessage) genericPackageMessage;
        String type = fullStatusMessage.getType().toString();
        String zoneInfo = componentHandler.handleZoneInfo(fullStatusMessage.getZoneInfo());
        String centralModel = componentHandler.handleCentralModel(fullStatusMessage.getCentralModel());
        String firmwareVersion = componentHandler.handleFirmwareVersion(fullStatusMessage.getFirmwareVersion());
        String partitionStatus = componentHandler.handlePartitionStatus(fullStatusMessage.getPartitionStatus());
        String partitions = componentHandler.handlePartitions(fullStatusMessage.getPartitions());
        String centralInfo = componentHandler.handleCentralInfo(fullStatusMessage.getCentralInfo());
        String dateTime = componentHandler.handleDateTime(fullStatusMessage.getDateTime());
        String powerInfo = componentHandler.handlePowerInfo(fullStatusMessage.getPowerInfo());
        String keyboards = componentHandler.handleKeyboards(fullStatusMessage.getKeyboards());
        String receivers = componentHandler.handleReceivers(fullStatusMessage.getReceivers());
        String battery = componentHandler.handleBattery(fullStatusMessage.getBattery());
        String pgmExpanders = componentHandler.handlePgmExpanders(fullStatusMessage.getPgmExpanders());
        String zoneExpanders = componentHandler.handleZoneExpanders(fullStatusMessage.getZoneExpanders());
        String sirenInfo = componentHandler.handleSirenInfo(fullStatusMessage.getSirenInfo());

        boolean phoneLineIsCut = fullStatusMessage.isPhoneLineIsCut();
        boolean failedToCommunicateEvent = fullStatusMessage.isFailedToCommunicateEvent();
        String communicationProblems = componentHandler.handleCommunicationProblems(phoneLineIsCut, failedToCommunicateEvent);

        String pgms = componentHandler.handlePgms(fullStatusMessage.getPgms());

        Collections.addAll(contentList, type, zoneInfo, centralModel, firmwareVersion,
                partitionStatus, partitions, centralInfo, dateTime, powerInfo, keyboards, receivers,
                battery, pgmExpanders, zoneExpanders, sirenInfo, communicationProblems, pgms);
        return contentList;
    }
}
