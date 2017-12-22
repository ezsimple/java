#!/bin/sh
TOKEN=bH3LhGY0OsqHBL7wlUnP9Glf9faAWPXR
function pushNotification () {
    msg=$1
    curl --header "Access-Token: ${TOKEN}" -X POST https://api.pushbullet.com/v2/ephemerals --header "Content-Type: application/json" --data-binary '{"type": "push", "push": {"type": "messaging_extension_reply","package_name": "com.pushbullet.android","source_user_iden": "iden","target_device_iden": "device_idens", "conversation_iden": "sms_phone_number","message": "Hello" } }'
}
pushNotification "for Test"
